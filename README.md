oc rsh <pod> -n <namespace>

echo | openssl s_client -connect ms-onefcc.global-core.svc.cluster.local:8080 2>/dev/null \
| openssl x509 > /tmp/ms-onefcc.crt


oc cp <namespace>/<pod>:/tmp/ms-onefcc.crt ./ms-onefcc.crt

openssl x509 -in ms-onefcc.crt -text -noout
