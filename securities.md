# Vulnerability Analysis

##broken acces control
###description
Exploitation of access control

###risk
illegally acting as administrative functions 
###counter-measures
the absence and simplicity of the program, the program does not need any authentication/

even if authentication is present, only 1 role wil exist

##injection

###description
Injection flaws occur when an attacker can send hostile data to an interpreter.

###risk
can result in data loss, corruption, or disclosure to unauthorized parties, loss of accountability, or denial of access. Injection can sometimes lead to complete host takeover.
The business impact depends on the needs of the application and data.


###counter-measures
the customer is restricted in sending data to the server, only strings are accepted

 if authentication has been added, players will only have acces to their own games