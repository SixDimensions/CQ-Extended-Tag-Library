export $DIST=/var/www/html
echo "Updating Website Code"
date
/usr/bin/git pull
jekyll $DIST --no-auto
echo "Update Complete"
