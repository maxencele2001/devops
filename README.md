# devops

First of all, we'll need to run ssh and get the ssh key from the output of this jenkins pipeline.
Next step : write this key down on the entry parameter of the terraform pipeline then run this one.
The terraform instance is now running.
Actually, the pipeline ansible isn't working, nevertheless, we should need the IP output from the terraform pipeline to be fill in the entry paramter with