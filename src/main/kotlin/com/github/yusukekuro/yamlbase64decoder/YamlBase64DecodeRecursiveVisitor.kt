package com.github.yusukekuro.yamlbase64decoder

import com.intellij.openapi.command.WriteCommandAction
import org.jetbrains.yaml.psi.YAMLQuotedText
import org.jetbrains.yaml.psi.YAMLScalar
import org.jetbrains.yaml.psi.YAMLValue
import org.jetbrains.yaml.psi.YamlRecursivePsiElementVisitor
import org.jetbrains.yaml.psi.impl.YAMLPlainTextImpl
import java.nio.charset.StandardCharsets
import java.util.Base64

/**
 * Same as text in plugin.xml
 */
private const val WRITE_COMMAND_NAME = "Decode Base64 Values"
private const val WRITE_COMMAND_GROUP_ID = "DECODE_BASE64_VALUES_GROUP_ID"

class YamlBase64DecodeRecursiveVisitor : YamlRecursivePsiElementVisitor() {

    override fun visitValue(value: YAMLValue) {
        if (!value.text.isNullOrBlank() && isDecodeTarget(value)) {
            try {
                val decodedTextByteArray = decodeBase64(value)
                if (isUtf8(decodedTextByteArray)) {
                    replaceText(value as YAMLScalar, String(decodedTextByteArray, StandardCharsets.UTF_8))
                }
            } catch (ignore: IllegalArgumentException) {
                // Base64.getDecoder throws IllegalArgumentException if text is not valid Base64
            }
        }
        super.visitValue(value)
    }

    private fun isDecodeTarget(value: YAMLValue): Boolean {
        // Ideally we should avoid using Impl but unfortunately YAMLPlainTextImpl does not have unique interface to check
        return value is YAMLPlainTextImpl || value is YAMLQuotedText
    }

    private fun decodeBase64(value: YAMLValue): ByteArray {
        // textValue removes beginning and ending quotes for YAMLQuotedText
        return Base64.getDecoder().decode((value as YAMLScalar).textValue)
    }

    private fun isUtf8(byteArray: ByteArray): Boolean {
        val utf8ByteArray = String(byteArray, StandardCharsets.UTF_8).toByteArray(StandardCharsets.UTF_8)
        return byteArray.contentEquals(utf8ByteArray)
    }

    private fun replaceText(value: YAMLScalar, newText: String) {
        // Use WriteCommandAction.runWriteCommandAction to support undo operation
        WriteCommandAction.runWriteCommandAction(
            value.project,
            WRITE_COMMAND_NAME,
            // groupID is used to group the target of undo operation
            WRITE_COMMAND_GROUP_ID,
            {
                // updateText retains quotes in text of YAMLQuotedText
                value.updateText(newText)
            },
            value.containingFile
        )
    }
}