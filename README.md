Project provides *two servlets* which help in debugging classloading issues in Java web applications.

This is a [WizTools.org](http://www.wiztools.org/) project.

### JavaEE Compatibility Version

The servlets are compatible with Servlet 3.0 specification, and should be used in JavaEE 6.0+ applications only.

### Installation

Place the Jar file inside your web-application's _/WEB-INF/lib_ directory.

### Usage

After placing the Jar in your application's /WEB-INF/lib directory, during application context startup, you can find in your app-server logs:

```
####################################
## Classloader Servlet Is Enabled ##
## NOT RECOMMENDED FOR PRODUCTION ##
##================================##
## Usage:                         ##
##    /ctx/class-source?class=[]  ##
##    /ctx/list-classpath         ##
####################################
```

This means the servlet is registered correctly.

### Servlet to Find From Where a Class is Loaded From

`http://<host>:<port>/<app-ctx>/class-source?class=fully.qualified.ClassName`

Sample response:

[![](http://farm9.staticflickr.com/8003/7302201150_003809084f_n.jpg)](http://www.flickr.com/photos/subwiz/7302201150/)

### Servlet to List Classpath

Listing of servlet classpath helps in understanding *in which order* the Jars and classes folder are loaded by the classloader.

`http://<host>:<port>/<app-ctx>/list-classpath`

Sample response:

[![](http://farm8.staticflickr.com/7082/7302202570_65d52b56c7_n.jpg)](http://www.flickr.com/photos/subwiz/7302202570/)
