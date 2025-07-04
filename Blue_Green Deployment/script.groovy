pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "krishna1405/mywebsite"
        VERSION = "v1.0.4"
        GREEN_SERVER = "ubuntu@3.108.55.108"
        BLUE_SERVER = "ubuntu@35.154.224.182"
        PEM_PATH = "C:\\Users\\eswan\\Downloads\\personal-lap.pem"
    }

    stages {

        stage('Setup Blue-Green Logic') {
            steps {
                script {
                    // This can come from a file, param, metadata, etc.
                    LIVE_SERVER = "BLUE"
                    IDLE_SERVER = (LIVE_SERVER == "BLUE") ? "GREEN" : "BLUE"
                    TARGET_SERVER = (IDLE_SERVER == "GREEN") ? env.GREEN_SERVER : env.BLUE_SERVER

                    echo "Live Server: ${LIVE_SERVER}"
                    echo "Idle Server: ${IDLE_SERVER}"
                    echo "Target IP for deployment: ${TARGET_SERVER}"
                }
            }
        }

        stage('Deploy to Target Server') {
            steps {
                script {
                    bat """
                        echo Deploying to ${TARGET_SERVER}...
                        ssh -i "${env.PEM_PATH}" -o StrictHostKeyChecking=no ${TARGET_SERVER} ^
                        "docker pull ${env.DOCKER_IMAGE}:${env.VERSION} && \
                         docker stop mywebsite || true && \
                         docker rm mywebsite || true && \
                         docker run -d --name mywebsite -p 80:80 ${env.DOCKER_IMAGE}:${env.VERSION}"
                    """
                }
            }
        }

        stage('Health Check') {
            steps {
                script {
                    def TARGET_IP = (IDLE_SERVER == "GREEN") ? "3.108.55.108" : "35.154.224.182"
                    bat "curl -f http://${TARGET_IP} || exit 1"
                }
            }
        }

        stage('Switch Traffic') {
            steps {
                echo "✅ Traffic would be switched to ${IDLE_SERVER} (e.g., via Nginx, ALB, Route53)"
            }
        }

        stage('Clean Up Old Server') {
            steps {
                script {
                    def OLD_SERVER = (LIVE_SERVER == "GREEN") ? env.GREEN_SERVER : env.BLUE_SERVER
                    bat """
                        echo Cleaning up old container on ${OLD_SERVER}...
                        ssh -i "${env.PEM_PATH}" -o StrictHostKeyChecking=no ${OLD_SERVER} ^
                        "docker stop mywebsite || true && docker rm mywebsite || true"
                    """
                }
            }
        }
    }
}
