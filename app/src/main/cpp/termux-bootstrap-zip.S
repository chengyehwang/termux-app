     .global blob
     .global blob_size
     .section .rodata
 blob:
 #if defined __aarch64__
     .incbin "bootstrap-aarch64.zip"
 #endif
 1:
 blob_size:
     .int 1b - blob
