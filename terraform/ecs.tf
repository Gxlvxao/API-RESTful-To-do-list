resource "aws_ecs_cluster" "main" {
  name = "gerenciador-tarefas-cluster"
}

resource "aws_iam_role" "ecs_task_execution_role" {
  name = "ecs_task_execution_role"
  assume_role_policy = jsonencode({
    Version = "2012-10-17",
    Statement = [{
      Action = "sts:AssumeRole",
      Effect = "Allow",
      Principal = {
        Service = "ecs-tasks.amazonaws.com"
      }
    }]
  })
}

resource "aws_iam_role_policy_attachment" "ecs_task_execution_role_policy" {
  role       = aws_iam_role.ecs_task_execution_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy"
}

resource "aws_ecs_task_definition" "api" {
  family                   = "gerenciador-tarefas-api"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn

  container_definitions = jsonencode([{
    name      = "gerenciador-tarefas-api",
    image     = "${aws_ecr_repository.api.repository_url}:latest",
    essential = true,
    portMappings = [{
      containerPort = 8080,
      hostPort      = 8080
    }],
    environment = [
      {
        name  = "SPRING_DATASOURCE_URL",
        value = "jdbc:postgresql://${aws_db_instance.default.address}:${aws_db_instance.default.port}/${aws_db_instance.default.db_name}"
      },
      {
        name  = "SPRING_DATASOURCE_USERNAME",
        value = var.db_username
      },
      {
        name  = "SPRING_DATASOURCE_PASSWORD",
        value = var.db_password
      }
    ],
    logConfiguration = {
        logDriver = "awslogs",
        options = {
            "awslogs-group" = "/ecs/gerenciador-tarefas-api",
            "awslogs-region" = "us-east-1",
            "awslogs-stream-prefix" = "ecs"
        }
    }
  }])
}

resource "aws_cloudwatch_log_group" "ecs_api" {
  name = "/ecs/gerenciador-tarefas-api"
}