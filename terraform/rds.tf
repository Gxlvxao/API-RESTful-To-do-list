resource "aws_security_group" "rds" {
  name        = "gerenciador-tarefas-rds-sg"
  description = "Allow inbound traffic to RDS"
  vpc_id      = aws_vpc.main.id

  tags = {
    Name = "gerenciador-tarefas-rds-sg"
  }
}

resource "aws_db_subnet_group" "default" {
  name       = "gerenciador-tarefas-subnet-group"
  subnet_ids = [aws_subnet.public_a.id, aws_subnet.public_b.id]

  tags = {
    Name = "gerenciador-tarefas-db-subnet-group"
  }
}

resource "aws_db_instance" "default" {
  allocated_storage    = 10
  engine               = "postgres"
  engine_version       = "15"
  instance_class       = "db.t3.micro"
  db_name              = "gerenciador_tarefas"
  username             = var.db_username
  password             = var.db_password
  db_subnet_group_name = aws_db_subnet_group.default.name
  vpc_security_group_ids = [aws_security_group.rds.id]
  skip_final_snapshot  = true
}