# Environment var
variable "env" {
  type        = string
  default     = "dev"
  description = "Environment"
}

# Instance ssh key name
variable "ssh_key_maxence_bug" {
  type        = string
  default     = "ssh_key_maxence_bug"
  description = "Instance ssh key name"
}

variable "instance_count" {
  type      = number
  default   = 1
  description = "Instance number"
}

variable "aws-region" {
  type        = string
  default = "us-east-2"
}

variable "aws-profile" {
  type        = string
  default = "default"
}

variable "ami" {
  type        = string
  default = "ami-0d97ef13c06b05a19"
}

variable "instance_type" {
  type    = string
  default = "t2.micro"
}
