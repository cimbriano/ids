#!/bin/sh

export LD_LIBRARY_PATH="./lib/jpcap:$LD_LIBRARY_PATH"
export JAVAOPTS="-classpath ./lib:./bin:./lib/jpcap/jpcap.jar"
export JAVA="java"
export MAIN="ids.IDS"

if [ "$1" = "all" ]; then   
    ${JAVA} ${JAVAOPTS} ${MAIN} etc/rules/blame_attack1.txt etc/pcap_files/trace1.pcap
    ${JAVA} ${JAVAOPTS} ${MAIN} etc/rules/blame_attack2.txt etc/pcap_files/trace2.pcap
    ${JAVA} ${JAVAOPTS} ${MAIN} etc/rules/blame_attack3.txt etc/pcap_files/trace3.pcap
    ${JAVA} ${JAVAOPTS} ${MAIN} etc/rules/plaintext_pop.txt etc/pcap_files/trace4.pcap
    ${JAVA} ${JAVAOPTS} ${MAIN} etc/rules/xmas_scan.txt etc/pcap_files/trace5.pcap
    ${JAVA} ${JAVAOPTS} ${MAIN} etc/rules/null_scan.txt etc/pcap_files/trace6.pcap
    ${JAVA} ${JAVAOPTS} ${MAIN} etc/rules/tftp_remote_boot.txt etc/pcap_files/trace7.pcap
elif [ 2 -eq $# ]; then
    ${JAVA} ${JAVAOPTS} ${MAIN} $1 $2
else
    echo "Usage: $0 [rule_file] [pcap_file]"
fi

