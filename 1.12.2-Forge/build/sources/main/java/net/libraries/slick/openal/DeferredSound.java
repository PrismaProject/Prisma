package net.libraries.slick.openal;

import java.io.IOException;
import java.io.InputStream;

import net.libraries.slick.loading.LoadingList;
import net.libraries.slick.util.Log;
import net.libraries.slick.loading.DeferredResource;

/**
 * A sound implementation that can load the actual sound file at a later 
 * point.
 *
 * @author kevin
 */
public class DeferredSound extends AudioImpl implements DeferredResource {
	/** Indicate a OGG to be loaded */
	public static final int OGG = 1;
	/** Indicate a WAV to be loaded */
	public static final int WAV = 2;
	/** Indicate a MOD/XM to be loaded */
	public static final int MOD = 3;
	/** Indicate a AIF to be loaded */
	public static final int AIF = 4;
	
	/** The type of sound to be loader */
	private int type;
	/** The location of the sound this proxy wraps */
	private String ref;
	/** The loaded sound if it's already been brought up */
	private Audio target;
	/** The input stream to load the sound this proxy wraps from (can be null) */
	private InputStream in;
	
	/**
	 * Create a new sound on request to load
	 * 
	 * @param ref The location of the sound to load
	 * @param type The type of sound to load
	 * @param in The input stream to load from
	 */
	public DeferredSound(String ref, InputStream in, int type) {
		this.ref = ref;
		this.type = type;
		
		// nasty hack to detect when we're loading from a stream
		if (ref.equals(in.toString())) {
			this.in = in;
		}
		
		LoadingList.get().add(this);
	}

	/**
	 * Check if the target has already been loaded
	 */
	private void checkTarget() {
		if (target == null) {
			throw new RuntimeException("Attempt to use deferred sound before loading");
		}
	}
	
	/**
	 * @see DeferredResource#load()
	 */
	public void load() throws IOException {
		boolean before = SoundStore.get().isDeferredLoading();
		SoundStore.get().setDeferredLoading(false);
		if (in != null) {
			switch (type) {
			case OGG:
				target = SoundStore.get().getOgg(in);
				break;
			case WAV:
				target = SoundStore.get().getWAV(in);
				break;
			case MOD:
				target = SoundStore.get().getMOD(in);
				break;
			case AIF:
				target = SoundStore.get().getAIF(in);
				break;
			default:
				Log.error("Unrecognised sound type: "+type);
				break;
			}
		} else {
			switch (type) {
			case OGG:
				target = SoundStore.get().getOgg(ref);
				break;
			case WAV:
				target = SoundStore.get().getWAV(ref);
				break;
			case MOD:
				target = SoundStore.get().getMOD(ref);
				break;
			case AIF:
				target = SoundStore.get().getAIF(ref);
				break;
			default:
				Log.error("Unrecognised sound type: "+type);
				break;
			}
		}
		SoundStore.get().setDeferredLoading(before);
	}

	/**
	 * @see AudioImpl#isPlaying()
	 */
	public boolean isPlaying() {
		checkTarget();
		
		return target.isPlaying();
	}

	/**
	 * @see AudioImpl#playAsMusic(float, float, boolean)
	 */
	public int playAsMusic(float pitch, float gain, boolean loop) {
		checkTarget();
		return target.playAsMusic(pitch, gain, loop);
	}

	/**
	 * @see AudioImpl#playAsSoundEffect(float, float, boolean)
	 */
	public int playAsSoundEffect(float pitch, float gain, boolean loop) {
		checkTarget();
		return target.playAsSoundEffect(pitch, gain, loop);
	}

	/**
	 * Play this sound as a sound effect
	 * 
	 * @param pitch The pitch of the play back
	 * @param gain The gain of the play back
	 * @param loop True if we should loop
	 * @param x The x position of the sound
	 * @param y The y position of the sound
	 * @param z The z position of the sound
	 */
	public int playAsSoundEffect(float pitch, float gain, boolean loop, float x, float y, float z) {
		checkTarget();
		return target.playAsSoundEffect(pitch, gain, loop, x, y, z);
	}
	
	/**
	 * @see AudioImpl#stop()
	 */
	public void stop() {
		checkTarget();
		target.stop();
	}

	/**
	 * @see DeferredResource#getDescription()
	 */
	public String getDescription() {
		return ref;
	}

}
