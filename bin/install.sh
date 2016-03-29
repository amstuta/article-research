sudo apt-get install cassandra
sudo service cassandra start

cqlsh -f cassandra_setup.cql localhost

sudo apt-get install scala
sudo apt-get install sbt