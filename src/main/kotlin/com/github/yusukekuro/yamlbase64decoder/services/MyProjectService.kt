package com.github.yusukekuro.yamlbase64decoder.services

import com.intellij.openapi.project.Project
import com.github.yusukekuro.yamlbase64decoder.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
