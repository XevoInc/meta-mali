require mali-userspace.inc

TYPE = "mali-t62x"

SRC_URI = "${MALI_URI}/${PV}-${PR}/${TYPE}_${PV}-${PR}_linux_1+fbdev.tar.gz;name=fbdev \
	${MALI_URI}/${PV}-${PR}/${TYPE}_${PV}-${PR}_linux_1+x11.tar.gz;name=x11 \
	${MALI_URI}/${PV}-${PR}/${TYPE}_${PV}-${PR}_linux_1+wayland.tar.gz;name=wayland"

SRC_URI[fbdev.md5sum] = "3f87441b5edb5a3ac21dd7544b00e638"
SRC_URI[fbdev.sha256sum] = "907d2f9da0ebbc671dadc1b73585c334a1fc6e028851fd37cd8b96901d580703"
SRC_URI[x11.md5sum] = "d421511c11fddbc8c3c28337b261939f"
SRC_URI[x11.sha256sum] = "246b4197db893f3b55ef34e2026095ef7dc749f70cdbae8efab3c521c79ba6cd"
SRC_URI[wayland.md5sum] = "42541ea11a0bf4089e1a848c3ada377a"
SRC_URI[wayland.sha256sum] = "d46a20b8155e8b9268d43184c8f04afd24cb85c41be20ddb945f49faf740f79a"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://END_USER_LICENCE_AGREEMENT.txt;md5=3918cc9836ad038c5a090a0280233eea"
