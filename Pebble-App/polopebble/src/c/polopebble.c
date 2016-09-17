#include "pebble.h"

static Window *s_main_window;
static TextLayer *s_heading_layer, *s_text_layer_calib_state;

static void init(){

}

static void deinit(){

}

int main(void) {
    init();
    app_event_loop();
    deinit();
    return 0;
}
