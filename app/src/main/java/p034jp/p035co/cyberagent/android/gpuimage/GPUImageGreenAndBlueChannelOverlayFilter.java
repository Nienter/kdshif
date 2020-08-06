package p034jp.p035co.cyberagent.android.gpuimage;

/* renamed from: jp.co.cyberagent.android.gpuimage.l */
public class GPUImageGreenAndBlueChannelOverlayFilter extends GPUImageFilter {
    public GPUImageGreenAndBlueChannelOverlayFilter() {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n    vec4 image = texture2D(inputImageTexture, textureCoordinate); \n    vec4 base = vec4(image.g,image.g,image.g,1.0); \n     vec4 overlay = vec4(image.b,image.b,image.b,1.0); \n    float ba = 2.0 * overlay.b * base.b + overlay.b * (1.0 - base.a) + base.b * (1.0 - overlay.a); \n    gl_FragColor = vec4(ba,ba,ba,image.a);\n}");
    }
}
