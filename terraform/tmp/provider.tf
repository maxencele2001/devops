# providers config
#

terraform {
  required_providers {
    aws = {
      source = "hashicorp/aws"
      version = "~> 3.35"
    }
  }
}

provider "aws" {
  profile                 = var.aws-profile
  region                  = var.aws-region
  shared_credentials_file = "./aws/credentials-ynov"
}

