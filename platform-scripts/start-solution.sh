cd amq-streams
echo "Starting Kafka in background"
./start_kafka.sh
cd ../..
cd target
java -jar idaas-datahub.jar $@
