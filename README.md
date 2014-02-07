This is a simple usage of the [airlift.io] platform meant to be used as an example
of various best practices.

Simple usage of:

* Dependency injection (via guice)
* Configuration
* RESTful JSON calls
* Logging
* Unit tests

The project was generated using the [maven] archetype generator:

```sh
mvn archetype:generate -U -B \
  -DarchetypeGroupId=io.airlift \
  -DarchetypeArtifactId=skeleton-server-archetype \
  -DarchetypeVersion=0.84 \
  -DgroupId=io.airlift \
  -DartifactId=service-monitor \
  -Dpackage=com.tellapart.monitoring
```

Package
------

This project can be packaged with [maven]:

```sh
mvn package
```

Run
-----

After packaging, to run the example server, do the following from the project
root directory:


```sh
cd target
tar xvzf airlift-example-1.0-SNAPSHOT.tar.gz
cd airlift-example-1.0-SNAPSHOT
ln -s ../../etc
touch etc/jvm.config
bin/launcher run
```

Test
-----

In a separate terminal window, the following commands should run as follows:

Exercise the HelloResource
```sh
$ curl localhost:8080/v1/hello/david
{"name":"david"}
$ curl localhost:8080/v1/hello/david?pretty
{
   "name":"david"
}
```

Exercise the ConfigurableHelloResource
```sh
$ curl localhost:8080/v2/hello/david
{"salutations":"Hola, david"}
```

Illustrate unbound URI
```sh
$ curl localhost:8080/v1.1/hello/david
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1"/>
<title>Error 404 Not Found</title>
</head>
<body>
<h2>HTTP ERROR: 404</h2>
<p>Problem accessing /v1.1/hello/david. Reason:
<pre>    Not Found</pre></p>
<hr /><i><small>Powered by Jetty://</small></i>
</body>
</html>
```

[maven]:http://maven.apache.org/
[airlift.io]:https://github.com/airlift/airlift
