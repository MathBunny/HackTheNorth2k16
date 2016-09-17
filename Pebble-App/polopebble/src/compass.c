#include "pebble.h"
#include "compass_window.h"
#include "compass_calibration_window.h"

static CompassWindow *compass_window;

static void init(void) {
    compass_window = compass_window_create();
    window_stack_push(compass_window_get_window(compass_window), true);
}

static void deinit(void) {
    compass_window_destroy(compass_window);
}

int main(void) {
    init();
    app_event_loop();
    deinit();
    return 0;
}
