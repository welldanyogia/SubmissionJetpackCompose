buildscript {
    val TOKEN by extra("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJ1c2VyLXc4WUJ2Wm9ZckozcVNZNEwiLCJpYXQiOjE2OTk1MTYwMjF9.7189ZPGY8886xeI0QrRBETVDVSSFm77GJxfDv8WrPQY")
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}