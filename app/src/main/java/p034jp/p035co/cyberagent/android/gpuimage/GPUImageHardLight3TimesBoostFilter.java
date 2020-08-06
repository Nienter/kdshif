package p034jp.p035co.cyberagent.android.gpuimage;

/* renamed from: jp.co.cyberagent.android.gpuimage.n */
public class GPUImageHardLight3TimesBoostFilter extends GPUImageFilter {
    public GPUImageHardLight3TimesBoostFilter() {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     vec4 color = texture2D(inputImageTexture,textureCoordinate); \n      \n     float hardLightColor = color.b; \n     for (int i = 0; i < 3; ++i) \n     { \n         if (hardLightColor < 0.5) { \n             hardLightColor = hardLightColor  * hardLightColor * 2.; \n         } else { \n             hardLightColor = 1. - (1. - hardLightColor) * (1. - hardLightColor) * 2.; \n         } \n     } \n      \n     float k = 255.0 / (164.0 - 75.0); \n     hardLightColor = (hardLightColor - 75.0 / 255.0) * k; \n      \n     gl_FragColor = vec4(vec3(hardLightColor),color.a); \n}");
    }
}
