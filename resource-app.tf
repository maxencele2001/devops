data "template_file" "user_data" {
  template = file("./cloud-init/scripts/add-ssh-web-app.yaml")
}

resource "aws_instance" "app_instance" {

  count = var.instance_count
  vpc_security_group_ids = [aws_security_group.allow_ssh.id]
  key_name = var.ssh_key_name
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

resource "aws_security_group" "allow_ssh" {
  name        = "allow_ssh"
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

resource "aws_key_pair" "ssh_key_maxence" {
  key_name   = "ssh_key_maxence"
  public_key = file("./key/id_rsa.pub")
}