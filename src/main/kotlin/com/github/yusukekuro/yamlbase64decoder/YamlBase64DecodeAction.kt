package com.github.yusukekuro.yamlbase64decoder

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import org.jetbrains.yaml.psi.YAMLFile

class YamlBase64DecodeAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val yamlPsiFile = e.getData(CommonDataKeys.PSI_FILE) as YAMLFile
        yamlPsiFile.accept(YamlBase64DecodeRecursiveVisitor())
    }

    override fun update(e: AnActionEvent) {
        val isProjectOpen = e.project != null
        val isEditorActive = e.getData(CommonDataKeys.EDITOR) != null
        val isYamlFile = e.getData(CommonDataKeys.PSI_FILE) is YAMLFile
        val menuAllowed = isProjectOpen && isEditorActive && isYamlFile
        e.presentation.isEnabledAndVisible = menuAllowed
    }
}