param(
    [string]$TomcatHome = $env:TOMCAT_HOME
)

$ErrorActionPreference = "Stop"

if (-not $TomcatHome) {
    throw "TOMCAT_HOME is not set."
}

$shutdown = Join-Path $TomcatHome "bin\shutdown.bat"
if (-not (Test-Path $shutdown)) {
    throw "Tomcat shutdown script not found: $shutdown"
}

$env:CATALINA_HOME = $TomcatHome
$env:CATALINA_BASE = $TomcatHome
& $shutdown
Write-Host "Tomcat stop command sent."
