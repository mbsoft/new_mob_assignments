FROM node:alpine AS build-env
WORKDIR /usr/src/app
COPY package*.json ./
COPY . .

FROM node:alpine
COPY --from=build-env /usr/src/app /app
RUN rm /sbin/apk /sbin/arp /sbin/ifconfig /sbin/ifdown /sbin/ifup /sbin/iproute \
    /sbin/iprule /sbin/iptunnel /sbin/route /sbin/setconsole /sbin/switch_root \
    /bin/chmod /bin/chown /bin/dd /bin/echo /bin/ipcalc /bin/mkdir /bin/mount \
    /bin/mountpoint /bin/netstat /bin/ping /bin/ping6 /bin/rmdir /bin/setpriv \
    /bin/setserial /bin/sync /bin/touch /bin/umount /bin/uname /usr/bin/wget \
    /usr/bin/nslookup /usr/bin/traceroute /usr/bin/traceroute6 /usr/bin/whoami \
    /usr/bin/whois /usr/sbin/chroot /usr/sbin/delgroup /usr/sbin/deluser \
    /usr/sbin/nandwrite /usr/sbin/sendmail /usr/local/bin/npm /usr/local/bin/npx \
    /usr/local/bin/yarn /usr/local/bin/yarnpkg
WORKDIR /app
CMD [ "node", "index.js" ]
