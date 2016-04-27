# Because we cannot support both Wayland and X11 with single binary
# we have to mark X11 as conflicting package in core-image-weston.

CONFLICT_DISTRO_FEATURES_append = " x11"
