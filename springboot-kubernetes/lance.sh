#export SPRING_APPLICATION_JSON='{"server.port":8081}'

#java -jar ./productNotification-0.0.1-SNAPSHOT.jar --spring.config.location=./application.properties

oc project pmsv2-dev

oc apply -f 0-pvc-springboot-v1.0.0.yam1

oc get pvc

oc apply f 1-springboot-v1.0.0.yaml

oc get deployment/pepit-sb

oc scale --replicas=0 deployment/pepit-sb

oc get deployment/pepit-sb

oc scale --replicas=1 deployment/pepit-sb; sleep 1; export podname=`oc get pods --selector=name=pepit-sb -o jsonpath='{.items [*].metadata.name}'`;

oc cp application.properties $podname:/var/lib/jarlib/

oc cp springb-0.0.1.jar $podname:/var/lib/jarlib/

oc get deployment/pepit-sb

export podname=`oc get pods --selector-name-pepit-sb -o jsonpath='{.items [*].metadata.name}'`

echo $podname

oc logs $podname

oc rsh $podname

------------
push to docker repo from pom.xml
https://www.geekyhacker.com/2019/07/14/how-to-use-spotify-docker-maven-plugin/


------------
create new-app from docker
https://ashishtechmill.com/running-simple-spring-boot-application-on-openshift


oc new-app registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift~https://github.com/eldagithub/openshift.git --context-dir=springboot-kubernetes --name=springboot-demo-openshift
oc logs -f bc/springboot-demo-openshift
oc expose svc/springboot-demo-openshift


oc get -o yaml dc,deployment,is,svc,route,secret,sa -l app=springboot-demo-openshift > export.yaml

oc create -f create-export.yaml

oc delete all,configmap,pvc,serviceaccount,rolebinding --selector app=springboot-demo2-openshift

oc delete all,configmap,pvc,serviceaccount,rolebinding --selector app=springboot-demo-openshift



------------
oc new-app registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift~https://github.com/fmarchioni/masterspringboot.git --context-dir=demo-docker --name=springboot-demo-docker

oc new-app registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift~https://github.com/eldagithub/openshift.git        --context-dir=springboot-kubernetes   —-name=springboot-demo-openshift

oc new-app registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift~https://github.com/fmarchioni/masterspringboot.git --context-dir=demo-docker --name=springboot-demo-docker

-----
OCP HPA 

apiVersion: autoscaling/v2beta2
kind: HorizontalPodAutoscaler
metadata:
  name: hpa-demo
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: springboot-demo-openshift
  minReplicas: 1
  maxReplicas: 3
  metrics:
    - type: Resource
      resource:
        name: cpu
        target:
          averageUtilization: 20
          type: Utilization
    - type: Resource
      resource:
        name: memory
        target:
          averageUtilization: 50
          type: Utilization

