/opt/solr/bin/solr start
#curl http://localhost:8983/solr/admin/cores?action=STATUS&core=product | xmllint --format -
#curl http://localhost:8983/solr/admin/cores?action=STATUS&core=order | xmllint --format -
#/opt/solr/bin/solr create_core -c product
#/opt/solr/bin/solr create_core -c order
/opt/solr/bin/solr stop