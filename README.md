# Article research
A simple scala / play program to search for articles online

# Install & launch
```sh
git clone git@github.com:amstuta/article-research.git
cd article-research
./bin/install.sh
sbt run
```

The installation script will install scala, sbt and cassandra.
It will also create cassandra keyspace "article_search" and table "raw_articles".
The application is now accessible on localhost:9000