# providers config
#

terraform {
<<<<<<< HEAD
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
  shared_credentials_file = "/mnt/c/Users/Maxence CROSSE/IdeaProjects/count/aws/credentials-ynov"
}
=======
}
>>>>>>> 3cb295dae2b11e338cea6ac065c595bba19c111f
