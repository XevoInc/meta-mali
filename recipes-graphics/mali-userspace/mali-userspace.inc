LICENSE = "Proprietary"
SECTION = "libs"

DEPENDS += "${@base_contains("DISTRO_FEATURES", "wayland", " mesa", " ", d)}"

PROVIDES += "virtual/egl virtual/libgles1 virtual/libgles2 virtual/libopencl"
PROVIDES += "${@base_contains("DISTRO_FEATURES", "wayland", " virtual/libwayland-egl", " ", d)}"

S = "${WORKDIR}"

MALI_URI = "http://malideveloper.arm.com/downloads/drivers/binary"

RDEPENDS_${PN} += "kernel-module-mali-kbase"

INSANE_SKIP_${PN} = "ldflags dev-so"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

USE_X11 = "${@base_contains("DISTRO_FEATURES", "x11", "yes", "no", d)}"
USE_DFB = "${@base_contains("DISTRO_FEATURES", "directfb", "yes", "no", d)}"
USE_WL = "${@base_contains("DISTRO_FEATURES", "wayland", "yes", "no", d)}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install () {
	# Create MALI manifest
	install -m 755 -d ${D}/${libdir}
	if [ "${USE_X11}" = "yes" ]; then
		install ${S}/x11/libmali.so ${D}/${libdir}
	elif [ "${USE_WL}" = "yes" ]; then
		install ${S}/wayland/libmali.so ${D}/${libdir}
	else
		install ${S}/fbdev/libmali.so ${D}/${libdir}
	fi

	ln -sf libmali.so ${D}/${libdir}/libEGL.so
	ln -sf libmali.so ${D}/${libdir}/libGLESv1_CM.so
	ln -sf libmali.so ${D}/${libdir}/libGLESv2.so
	ln -sf libmali.so ${D}/${libdir}/libOpenCL.so
	if [ "${USE_WL}" = "yes" ]; then
		ln -sf libmali.so ${D}/${libdir}/libgbm.so
		ln -sf libmali.so ${D}/${libdir}/libwayland-egl.so
	fi
}

PACKAGES = "${PN}"
FILES_${PN} += "${libdir}/*.so"

RREPLACES_${PN} = "libegl libegl1 libgles1 libglesv1-cm1 libgles2 libglesv2-2 libgbm"
RPROVIDES_${PN} = "libegl libegl1 libgles1 libglesv1-cm1 libgles2 libglesv2-2 libgbm"
RCONFLICTS_${PN} = "libegl libegl1 libgles1 libglesv1-cm1 libgles2 libglesv2-2 libgbm"
