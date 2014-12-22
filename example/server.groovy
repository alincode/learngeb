@GrabResolver(name='codehaus-release-repo', root='http://repository.codehaus.org')
@Grab(group='org.mortbay.jetty', module='jetty-embedded', version='6.1.11')

import org.mortbay.jetty.*
import org.mortbay.jetty.handler.*

def server = new Server(1234)
def root = new ContextHandler(server, "/")
root.setResourceBase("./src/test/resources/www_root")
root.addHandler(new ResourceHandler())
server.start()