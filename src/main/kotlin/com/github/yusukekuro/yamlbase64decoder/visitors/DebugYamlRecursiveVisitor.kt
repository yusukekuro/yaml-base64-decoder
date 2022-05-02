package com.github.yusukekuro.yamlbase64decoder.visitors

import org.jetbrains.yaml.psi.YAMLAlias
import org.jetbrains.yaml.psi.YAMLAnchor
import org.jetbrains.yaml.psi.YAMLCompoundValue
import org.jetbrains.yaml.psi.YAMLDocument
import org.jetbrains.yaml.psi.YAMLKeyValue
import org.jetbrains.yaml.psi.YAMLMapping
import org.jetbrains.yaml.psi.YAMLQuotedText
import org.jetbrains.yaml.psi.YAMLScalar
import org.jetbrains.yaml.psi.YAMLScalarList
import org.jetbrains.yaml.psi.YAMLScalarText
import org.jetbrains.yaml.psi.YAMLSequence
import org.jetbrains.yaml.psi.YAMLSequenceItem
import org.jetbrains.yaml.psi.YAMLValue
import org.jetbrains.yaml.psi.YamlRecursivePsiElementVisitor
import java.util.stream.Collectors

class DebugYamlRecursiveVisitor : YamlRecursivePsiElementVisitor() {
    override fun visitAlias(alias: YAMLAlias) {
        println("visitAlias: ${alias.aliasName}")
        super.visitAlias(alias)
    }

    override fun visitAnchor(anchor: YAMLAnchor) {
        println("visitAnchor: ${anchor.name}")
        super.visitAnchor(anchor)
    }

    override fun visitCompoundValue(compoundValue: YAMLCompoundValue) {
        println("visitCompoundValue: ${compoundValue.textValue}")
        super.visitCompoundValue(compoundValue)
    }

    override fun visitDocument(document: YAMLDocument) {
        println("visitDocument: ${document.topLevelValue?.tag?.text}")
        super.visitDocument(document)
    }

    override fun visitKeyValue(keyValue: YAMLKeyValue) {
        println("visitKeyValue: ${keyValue.keyText}=${keyValue.valueText}")
        super.visitKeyValue(keyValue)
    }

    override fun visitMapping(mapping: YAMLMapping) {
        println(
            "visitMapping: ${
                mapping.keyValues.stream()
                    .map { keyValue -> "${keyValue.keyText}=${keyValue.valueText}" }
                    .collect(Collectors.joining(", "))
            }"
        )
        super.visitMapping(mapping)
    }

    override fun visitSequenceItem(sequenceItem: YAMLSequenceItem) {
        println("visitSequenceItem: ${sequenceItem.value?.tag?.text}")
        super.visitSequenceItem(sequenceItem)
    }

    override fun visitQuotedText(quotedText: YAMLQuotedText) {
        println("visitQuotedText: ${quotedText.textValue}")
        super.visitQuotedText(quotedText)
    }

    override fun visitScalar(scalar: YAMLScalar) {
        println("visitScalar: ${scalar.textValue}")
        super.visitScalar(scalar)
    }

    override fun visitScalarList(scalarList: YAMLScalarList) {
        println("visitScalarList: ${scalarList.textValue}")
        super.visitScalarList(scalarList)
    }

    override fun visitScalarText(scalarText: YAMLScalarText) {
        println("visitScalarText: ${scalarText.textValue}")
        super.visitScalarText(scalarText)
    }

    override fun visitValue(value: YAMLValue) {
        println("visitValue: ${value.text}")
        super.visitValue(value)
    }

    override fun visitSequence(sequence: YAMLSequence) {
        println("visitSequence: ${sequence.textValue}")
        super.visitSequence(sequence)
    }
}