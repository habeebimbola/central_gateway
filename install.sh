#!/bin/sh
## Solution To The Task Exercise 2
#Author: ANIMASHAUN AMINAT


echo "Welcome! This Program helps to install NodeJS/Curl And wget executables if not already installed";

os_name=`uname -a`
curl_dir=`which curl | wc -l`
node_dir=`which node | wc -l`
wget_dir=`which wget | wc -l`


os_path=`exec echo $os_name`;
curlInstalled=`exec echo $curl_dir`;
nodeInstalled=`exec echo $node_dir`;
wgetInstalled=`exec echo $wget_dir`;



mac_os=`exec uname -a | grep -c -i 'MacBook*'`;
linux_os=`exec uname -a  | grep -c -i 'linux*'`;


if [ 1 -eq $mac_os ]; then
  echo 'Installing on a Mac System';

   if [ $curlInstalled -eq 0 ] ; then
         exec brew install curl ;
   fi

   if [ $nodeInstalled -eq 0 ] ; then
         exec brew install node;
   fi

   if [ $wgetInstalled -eq 0 ] ; then
        exec brew install wget;
   fi
  exit 0;
fi


if [ 1 -eq $linux_os ] ; then

   echo 'Installing on a Linux Machine';
  
   rpm=`which rpm | wc -l`;
   apt=`which apt | wc -l`;

   if [ $rpm -eq 1 ] ; then
 
       if [ $curlInstalled -eq 0 ] ; then
         exec rpm install curl ;
       fi
       
      if [ $nodeInstalled -eq 0 ] ; then
         exec rpm install node;
      fi
  
      if [ $wgetInstalled -eq 0 ] ; then
         exec rpm install wget;
      fi
     
      echo "Exiting installation....";
      exit 0
  fi

     if [ apt -eq 1 ] ; then

       if [ $curlInstalled -eq 0 ] ; then
         exec apt-get install curl ;
       fi

      if [ $nodeInstalled -eq 0 ] ; then
         exec apt-get install node;
      fi

      if [ $wgetInstalled -eq 0 ] ; then
         exec apt-get install wget;
      fi

      echo "Exiting installation....";
      exit 0
  fi

fi


