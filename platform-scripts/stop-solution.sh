echo "Stoping iDaas - Connect HL7"
cd ..
cd target
kill $(cat ./bin/shutdown.pid)

echo "Stoping Kafka running in background"
cd ../platform-scripts/amq-streams
./stop_kafka.sh
