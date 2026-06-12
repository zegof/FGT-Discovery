package de.itsgraphax.fgtDiscovery;

import de.itsgraphax.grphxLib.utils.RichText;

public abstract class HasPlugin {
    protected final FgtDiscovery plugin = FgtDiscovery.getInstance();
    protected final RichText.RichConfigText rt = plugin.richText();
}
