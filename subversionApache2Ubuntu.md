# Introduction #

## Artigo retirado da página: http://davidwinter.me.uk/articles/2006/02/16/subversion-over-apache-2-on-ubuntu/ ##


# Details #

Subversion over Apache 2 on Ubuntu

If your one of my regular readers (ha!), then you’ll know I’m starting a Group Project for University. We have 7 members in the group and without some sort of version control – managing the code we’re about to produce would be hell! So I’m setting up Subversion on my home server as a repository. Following are the steps I used to set-up Subversion over Apache 2 on my Ubuntu server.


I’m assuming you have Apache 2 already set-up. The only extra packages to download and install are `subversion` and `libapache2-svn`.
```
sudo apt-get install subversion libapache2-svn
```
This will download and install Subversion and the SVN module for Apache 2. The module itself uses WebDAV to transmit files between Subversion – so this means everything can go across port 80 without the hassle of having to worry about a firewall.

The install should automatically enable the module, but just to check:
```
sudo a2enmod dav_svn
```
It should come up saying it’s already enabled. If not, it will enable it for you.

You’ll need to configure Apache now:
```
sudo nano /etc/apache2/mods-enabled/dav_svn.conf
```
Edit the file to look something like this:
```
<Location /svn>
  DAV svn
  SVNPath /home/svn

  AuthType Basic
  AuthName "Subversion Repository"
  AuthUserFile /etc/apache2/dav_svn.passwd
  Require valid-user
</Location>
```
Change `/home/svn` to whatever the location of your repository is. If you haven’t created one yet, then do:
```
sudo mkdir /home/svn
sudo svnadmin create /home/svn
```
Now, you need to make Apache the owner of the repository:
```
sudo chown -R www-data /home/svn
```
To secure Subversion, do the following to create a password file:
```
sudo htpasswd2 -cm /etc/apache2/dav_svn.passwd bob
```
Replace bob with whatever username you want to use, and then when prompted enter a password.

Now restart Apache:
```
sudo /etc/init.d/apache2 restart
```
That should now all be set-up. You can try it by visiting your server `http://you.server/svn`. You should get a username/password dialog which you enter the details you created.