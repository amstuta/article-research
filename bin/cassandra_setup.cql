CREATE KEYSPACE article_search WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

CREATE TABLE article_search.raw_articles (
    url varchar PRIMARY KEY,
    title varchar,
    author varchar,
    content text,
    date bigint
);