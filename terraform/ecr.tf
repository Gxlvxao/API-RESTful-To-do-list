resource "aws_ecr_repository" "api" {
  name                 = "gerenciador-tarefas-api"
  image_tag_mutability = "MUTABLE"

  image_scanning_configuration {
    scan_on_push = true
  }
}