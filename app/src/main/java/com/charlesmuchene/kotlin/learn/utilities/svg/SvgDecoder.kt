package com.charlesmuchene.kotlin.learn.utilities.svg

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.caverock.androidsvg.SVG
import java.io.InputStream
import com.caverock.androidsvg.SVGParseException
import com.bumptech.glide.load.resource.SimpleResource
import timber.log.Timber
import java.io.IOException

/**
 * Decodes an SVG internal representation from an [InputStream]
 */
class SvgDecoder : ResourceDecoder<InputStream, SVG> {
    override fun handles(source: InputStream, options: Options) = true

    override fun decode(source: InputStream, width: Int, height: Int, options: Options): Resource<SVG>? {
        try {
            val svg = SVG.getFromInputStream(source)
            return SimpleResource<SVG>(svg)
        } catch (e: SVGParseException) {
            Timber.e(e)
            throw IOException("Cannot load SVG from stream", e)
        }
    }
}