package p034jp.p035co.cyberagent.android.gpuimage;

/* renamed from: jp.co.cyberagent.android.gpuimage.s */
public class GPUImageNormalBlendFilter extends GPUImageTwoInputFilter {
    public GPUImageNormalBlendFilter() {
        super("varying highp vec2 textureCoordinate;\n varying highp vec2 textureCoordinate2;\n \n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2;\n \n void main()\n {\n     lowp vec4 c2 = texture2D(inputImageTexture, textureCoordinate);\n     lowp vec4 c1 = texture2D(inputImageTexture2, textureCoordinate2);\n     gl_FragColor = c1 + (1.0 - c1.a) * c2;\n }");
    }
}
