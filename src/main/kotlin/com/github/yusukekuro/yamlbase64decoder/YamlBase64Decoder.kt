package com.github.yusukekuro.yamlbase64decoder

import com.github.yusukekuro.yamlbase64decoder.visitors.YamlBase64DecodeRecursiveVisitor
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import org.jetbrains.yaml.psi.YAMLFile

class YamlBase64Decoder : AnAction() {
    /**
     * Replace base64 encoded values with decoded string in following steps
     * <ol>
     *   <li>Parse file content as yaml object</li>
     *   <li>Recursively find keys that has only base64 encoded values</li>
     *     <ol>
     *       <li>Apply base64 decoding to the value</li>
     *       <li>Check if the decoded values is UTF-8</li>
     *     </ol>
     *   <li>Decoded values with base64</li>
     *   <li>Overwrite values with decoded string</li>
     *   <li>Replace file content with overwritten yaml object</li>
     * </ol>
     *
     * @param e Event related to this action
     */
    override fun actionPerformed(e: AnActionEvent) {
        val yamlPsiFile = e.getData(CommonDataKeys.PSI_FILE) as YAMLFile
        yamlPsiFile.accept(YamlBase64DecodeRecursiveVisitor())
    }

    /**
     * Enables and sets visibility of this action menu item if:
     * <ul>
     *   <li>a project is open</li>
     *   <li>an editor is active</li>
     *   <li>file type is yaml</li>
     * </ul>
     *
     * @param e Event related to this action
     */
    override fun update(e: AnActionEvent) {
        val isProjectOpen = e.project != null
        val isEditorActive = e.getData(CommonDataKeys.EDITOR) != null
        val isYamlFile = e.getData(CommonDataKeys.PSI_FILE) is YAMLFile
        val menuAllowed = isProjectOpen && isEditorActive && isYamlFile
        e.presentation.isEnabledAndVisible = menuAllowed
    }
}