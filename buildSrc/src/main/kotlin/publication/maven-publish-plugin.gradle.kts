import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("com.vanniktech.maven.publish")
}

mavenPublishing {
    val projectGitUrl = "https://github.com/Merseyside/material-range-bar"

    pom {
        name.set("Mersey android material range bar")
        description.set("oli107's android material range bar")
        url.set(projectGitUrl)

        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }
        developers {
            developer {
                id.set("Merseyside")
                name.set("Ivan Sablin")
                email.set("ivanklessablin@gmail.com")
            }
        }

        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }

        issueManagement {
            system.set("GitHub")
            url.set("$projectGitUrl/issues")
        }

        scm {
            connection.set("scm:git:$projectGitUrl")
            developerConnection.set("scm:git:$projectGitUrl")
            url.set(projectGitUrl)
        }
    }

    publishToMavenCentral(SonatypeHost.S01)
}

