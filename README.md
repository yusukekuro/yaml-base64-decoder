# yaml-base64-decoder

![Build](https://github.com/yusukekuro/yaml-base64-decoder/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/19099-yaml-base64-decoder.svg)](https://plugins.jetbrains.com/plugin/19099-yaml-base64-decoder)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/19099-yaml-base64-decoder.svg)](https://plugins.jetbrains.com/plugin/19099-yaml-base64-decoder)

## Plugin Description
<!-- Plugin description -->
#### Extension
- Adds `Decode Base64 Values` to editor popup menu when yaml file is opened
- Adds a keyboard shortcut `Cmd-Alt-Y` to run the decoder

#### Feature
- Automatically detects Base64 encoded values in a yaml file and replace them with decoded values

#### Usecase
- To see raw text in Kubernetes (k8s) Secret yaml file
- To see raw text in any yaml file that has Base64 encoded values
<!-- Plugin description end -->

## Installation

- Using IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "Yaml Base64 Decoder"</kbd> >
  <kbd>Install Plugin</kbd>
  
- Manually:

  Download the [latest release](https://github.com/yusukekuro/yaml-base64-decoder/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

## Development TODO
- High
  - `\\n` is added before 62th char in YAMLQuotedTextImpl.getEncodeReplacements if quote is double quote
    - Added in https://github.com/JetBrains/intellij-community/commit/78e38d46db58488451b641a39d11c7ba85454ac0
  - Check if shortcut key is working
- Low
  - Show hint after the command run
    - reference: ReformatCodeAction
  - Execute base64 decode against multiple selected yaml files

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
