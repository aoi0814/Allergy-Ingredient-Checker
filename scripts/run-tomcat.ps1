param(
    [string]$TomcatHome = $env:TOMCAT_HOME,
    [string]$WarPath = ".\target\test-webapp.war",
    [string]$ContextName = "test-webapp"
)

$ErrorActionPreference = "Stop"

if (-not $TomcatHome) {
    throw "TOMCAT_HOME is not set. Example: `$env:TOMCAT_HOME='C:\apache-tomcat-9.0.xx'"
}

if (-not (Test-Path $WarPath)) {
    throw "WAR not found: $WarPath. Run 'mvn clean package' first."
}

$webapps = Join-Path $TomcatHome "webapps"
$startup = Join-Path $TomcatHome "bin\startup.bat"

if (-not (Test-Path $webapps)) {
    throw "Tomcat webapps directory not found: $webapps"
}

if (-not (Test-Path $startup)) {
    throw "Tomcat startup script not found: $startup"
}

$targetWar = Join-Path $webapps "$ContextName.war"
Copy-Item $WarPath $targetWar -Force

Write-Host "Deployed WAR to: $targetWar"
$env:CATALINA_HOME = $TomcatHome
$env:CATALINA_BASE = $TomcatHome
& $startup
Write-Host "Tomcat started. Open: http://localhost:8080/$ContextName/"
