# PictureInPictureExample
Android 8.0 (API level 26) allows activities to launch in picture-in-picture (PIP) mode. PIP is a special type of multi-window mode mostly used for video playback. It lets the user watch a video in a small window pinned to a corner of the screen while navigating between apps or browsing content on the main screen.
It is working only 8.0.

## Aspect Ratio Code
 Rational aspectRatio = new Rational(vv.getWidth(), vv.getHeight());
        pictureInPictureParamsBuilder.setAspectRatio(aspectRatio).build();
        enterPictureInPictureMode(pictureInPictureParamsBuilder.build());
