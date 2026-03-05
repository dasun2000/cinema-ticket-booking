provider "aws" {
  region = "us-east-1"
}

resource "aws_security_group" "pricing_service_sg" {
  name        = "pricing-service-security-group"
  description = "Allow inbound traffic for Pricing Service"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_key_pair" "pricing_service_key" {
  key_name   = "pricing-service-key"
  public_key = file("${path.module}/pricing_service_key.pub")
}

data "aws_ami" "ubuntu" {
  most_recent = true
  owners      = ["099720109477"]

  filter {
    name   = "name"
    values = ["ubuntu/images/hvm-ssd/ubuntu-jammy-22.04-amd64-server-*"]
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }
}

resource "aws_instance" "pricing_service_ec2" {
  ami           = data.aws_ami.ubuntu.id
  instance_type = "t3.micro"
  key_name      = aws_key_pair.pricing_service_key.key_name
  vpc_security_group_ids = [aws_security_group.pricing_service_sg.id]

  user_data = <<-EOF
              #!/bin/bash
              sudo apt-get update -y
              sudo apt-get install -y docker.io git
              sudo systemctl start docker
              sudo systemctl enable docker
              sudo usermod -aG docker ubuntu
              EOF

  tags = {
    Name = "PricingServiceEC2Instance"
  }
}

output "ec2_public_ip" {
  value = aws_instance.pricing_service_ec2.public_ip
}
