provider "aws" {
  region = "us-east-1" # Change this as needed
}

# 1. Provide an internet-facing Security Group
resource "aws_security_group" "seat_service_sg" {
  name        = "seat-service-security-group"
  description = "Allow inbound traffic for Seat Service running on Docker"

  # SSH Access
  ingress {
    description = "SSH from anywhere"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  # Spring Boot Application Access
  ingress {
    description = "App Port"
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  # Standard Web Traffic
  ingress {
    description = "HTTP"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    description = "Allow all outbound traffic"
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "seat-service-sg"
  }
}

# 2. Find the Latest Ubuntu 22.04 AMI
data "aws_ami" "ubuntu" {
  most_recent = true
  owners      = ["099720109477"] # Canonical Number for Official Ubuntu AMIs

  filter {
    name   = "name"
    values = ["ubuntu/images/hvm-ssd/ubuntu-jammy-22.04-amd64-server-*"]
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }
}

# 3. Create Key Pair and EC2 Instance
resource "aws_key_pair" "seat_service_key" {
  key_name   = "seat-service-key"
  public_key = file("${path.module}/seat_service_key.pub")
}

resource "aws_instance" "seat_service_ec2" {
  ami           = data.aws_ami.ubuntu.id
  instance_type = "t3.micro" # AWS Free Tier Eligible
  key_name      = aws_key_pair.seat_service_key.key_name

  # Attach the security group we created
  vpc_security_group_ids = [aws_security_group.seat_service_sg.id]

  # Optional User Data script to install Docker immediately upon boot
  user_data = <<-EOF
              #!/bin/bash
              sudo apt-get update -y
              sudo apt-get install -y docker.io git
              sudo systemctl start docker
              sudo systemctl enable docker
              sudo usermod -aG docker ubuntu
              EOF

  tags = {
    Name = "SeatServiceEC2Instance"
  }
}

# 4. Output the public IP
output "ec2_public_ip" {
  description = "Public IP address of the EC2 instance"
  value       = aws_instance.seat_service_ec2.public_ip
}
