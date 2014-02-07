This is a simple usage of the airlift.io platform meant to be used as an example
of various best practices.

Simple usage of:

Dependency injection (via guice)
Configuration
RESTful JSON calls



The project was generated using the maven archetype generator:

mvn archetype:generate -U -B \
  -DarchetypeGroupId=io.airlift \
  -DarchetypeArtifactId=skeleton-server-archetype \
  -DarchetypeVersion=0.84 \
  -DgroupId=io.arilift \
  -DartifactId=service-monitor \
  -Dpackage=com.tellapart.monitoring


It can be packaged with: mvn package

After packaging, to run the sample server, do the following from the project
root directory:


######################################
cd target
tar xvzf airlift-example-1.0-SNAPSHOT.tar.gz
cd airlift-example-1.0-SNAPSHOT
cp -r ../../etc .
touch etc/jvm.config
bin/launcher run
######################################

In a separate terminal window, the following commands should run as follows:
$ curl localhost:8080/v1/hello/david
{"name":"david"}


$ curl localhost:8080/v2/hello/david
{"salutations":"Hola, david"}

$ curl localhost:8080/v3/hello/david
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1"/>
<title>Error 404 Not Found</title>
</head>
<body>
<h2>HTTP ERROR: 404</h2>
<p>Problem accessing /v3/hello/david. Reason:
<pre>    Not Found</pre></p>
<hr /><i><small>Powered by Jetty://</small></i>
</body>
</html>



