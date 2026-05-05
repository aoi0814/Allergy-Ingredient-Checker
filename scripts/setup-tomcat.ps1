param(
    [string]$Version = "9.0.105",
    [string]$InstallRoot = ".tools"
)

$ErrorActionPreference = "Stop"

$tomcatDir = Join-Path $InstallRoot ("apache-tomcat-" + $Version)
if (Test-Path $tomcatDir) {
    Write-Host "Tomcat already exists: $tomcatDir"
    Write-Host "Set environment variable: `$env:TOMCAT_HOME='$((Resolve-Path $tomcatDir).Path)'"
    exit 0
}

if (-not (Test-Path $InstallRoot)) {
    New-Item -ItemType Directory -Path $InstallRoot | Out-Null
}

$zipName = "apache-tomcat-$Version-windows-x64.zip"
$downloadUrl = "https://archive.apache.org/dist/tomcat/tomcat-9/v$Version/bin/$zipName"
$zipPath = Join-Path $InstallRoot $zipName

Write-Host "Downloading Tomcat from $downloadUrl"
Invoke-WebRequest -Uri $downloadUrl -OutFile $zipPath

Write-Host "Extracting $zipPath"
Expand-Archive -Path $zipPath -DestinationPath $InstallRoot -Force
Remove-Item $zipPath -Force

$resolved = (Resolve-Path $tomcatDir).Path
Write-Host "Installed: $resolved"
Write-Host "Run:"
Write-Host "  `$env:TOMCAT_HOME='$resolved'"
Write-Host "  powershell -ExecutionPolicy Bypass -File .\\scripts\\run-tomcat.ps1"
