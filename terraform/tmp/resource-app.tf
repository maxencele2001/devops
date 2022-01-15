data "template_file" "user_data" {
  template = file("./cloud-init/scripts/add-ssh-web-app.yaml")
}

resource "aws_instance" "app_instance" {

  count = var.instance_count
  vpc_security_group_ids = [aws_security_group.allow_ssh_bug.id]
  key_name = var.ssh_key_maxence_bug
  ami = var.ami
  instance_type = var.instance_type
  user_data = data.template_file.user_data.rendered
  tags = {
    Name = "${var.env}-Maxence-${count.index}"
    Environment = var.env
    Groups = "app"
    Owner = "maxence.crosse@ynov.com"
  }
}

resource "aws_security_group" "allow_ssh_bug" {
  name        = "allow_ssh_bug"
  description = "Allow ssh inbound traffic"

  ingress {
    from_port        = 22
    to_port          = 22
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]

  }

  tags = {
    Name = "allow_tls"
  }
}

resource "aws_key_pair" "ssh_key_maxence_bug" {
  key_name   = "ssh_key_maxence_bug"
  public_key = file("~/.ssh/id_rsa")
}