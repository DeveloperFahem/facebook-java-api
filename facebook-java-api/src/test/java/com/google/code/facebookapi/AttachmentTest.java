package com.google.code.facebookapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

public class AttachmentTest {
	@Test
	public void testStreamPublishProperties() throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		String message = "Facebook stream publish properties test.";
		Attachment attachment = createAttachment();

		attachment.setProperties( createPropertiesList() );

		Object result = client.stream_publish( message, attachment, null, null, null );

		Assert.assertNotNull( result );

		//streamRemove( result.toString() );
	}

	@Test
	public void testStreamPublishAdditionalInfo() throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		String message = "Facebook stream publish properties test.";
		Attachment attachment = createAttachment();

		attachment.setAdditionalInfo( createAdditionalInfoMap() );

		Object result = client.stream_publish( message, attachment, null, null, null );

		Assert.assertNotNull( result );

		streamRemove( result.toString() );
	}

	@Test
	public void testStreamPublishImage() throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		String message = "Facebook stream publish image test.";
		Attachment attachment = createAttachment();

		attachment.setMedia( createMediaImage() );

		Object result = client.stream_publish( message, attachment, null, null, null );

		Assert.assertNotNull( result );

		streamRemove( result.toString() );
	}

	@Test
	public void testStreamPublishFlash() throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		String message = "Facebook stream publish flash test.";
		Attachment attachment = createAttachment();

		attachment.setMedia( createMediaFlash() );

		Object result = client.stream_publish( message, attachment, null, null, null );

		Assert.assertNotNull( result );

		streamRemove( result.toString() );
	}

	@Test
	public void testStreamPublishMP3() throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		String message = "Facebook stream publish mp3 test.";
		Attachment attachment = createAttachment();

		attachment.setMedia( createMediaMP3() );

		Object result = client.stream_publish( message, attachment, null, null, null );

		Assert.assertNotNull( result );

		streamRemove( result.toString() );
	}

	@Test
	public void testStreamPublishVideo() throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		String message = "Facebook stream publish video test.";
		Attachment attachment = createAttachment();

		attachment.setMedia( createMediaVideo() );

		Object result = client.stream_publish( message, attachment, null, null, null );

		Assert.assertNotNull( result );

		streamRemove( result.toString() );
	}

	private Attachment createAttachment() {
		Attachment attachment = new Attachment();
		attachment.setName( "Test attachment" );
		attachment.setHref( "http://wiki.developers.facebook.com/index.php/Attachment_(Streams)" );
		attachment.setCaption( "The attachment object holds stuff." );
		attachment.setDescription( "This is a test attachment object." );

		return attachment;
	}

	private List<AttachmentProperty> createPropertiesList() {
		List<AttachmentProperty> properties = new ArrayList<AttachmentProperty>();

		properties.add( new AttachmentProperty( "my website", "abdinoor.com", null ) );
		properties.add( new AttachmentProperty( "twitter", "twitter/abdinoor", "http://twitter.com/abdinoor" ) );
		properties.add( new AttachmentProperty( "linkedin", "linkedin.com", "http://www.linkedin.com/pub/dan-abdinoor/3/3b5/708" ) );
		properties.add( new AttachmentProperty( "facebook", null, "http://www.facebook.com/abdinoor" ) );

		return properties;
	}

	private Map<String,String> createAdditionalInfoMap() {
		Map<String,String> map = new TreeMap<String,String>();

		map.put( "latitude", "42 22 25 N" );
		map.put( "longitude", "71 6 38 W" );

		return map;
	}

	private AttachmentMediaImage createMediaImage() {
		AttachmentMediaImage mediaImage = new AttachmentMediaImage();

		mediaImage.addImage( "http://icanhascheezburger.files.wordpress.com/2009/03/funny-pictures-kitten-finished-his-milk-and-wants-a-cookie.jpg",
				"http://icanhascheezburger.com/2009/03/30/funny-pictures-awlll-gone-cookie-now/" );

		return mediaImage;
	}

	private AttachmentMediaFlash createMediaFlash() {
		AttachmentMediaFlash mediaFlash = new AttachmentMediaFlash( "http://www.mapsofwar.com/images/EMPIRE17.swf",
				"http://icanhascheezburger.files.wordpress.com/2009/04/funny-pictures-hairless-cat-phones-home.jpg", 100, 80, 320, 260 );

		return mediaFlash;
	}

	private AttachmentMediaMP3 createMediaMP3() {
		AttachmentMediaMP3 mediaMP3 = new AttachmentMediaMP3( "http://www.looptvandfilm.com/blog/Radiohead%20-%20In%20Rainbows/01%20-%20Radiohead%20-%2015%20Step.MP3",
				"20 Step", "Radiohead", "In Rainbows" );

		return mediaMP3;
	}

	private AttachmentMediaVideo createMediaVideo() {
		AttachmentMediaVideo mediaVideo = new AttachmentMediaVideo( "http://www.youtube.com/v/fzzjgBAaWZw&hl=en&fs=1",
				"http://icanhascheezburger.files.wordpress.com/2009/04/funny-pictures-hairless-cat-phones-home.jpg", "kitty", "application/x-shockwave-flash",
				"http://icanhascheezburger.com" );

		return mediaVideo;
	}

	/** Used by various unit tests to remove stream item. */
	private void streamRemove( final String postId ) throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		Object result = client.stream_remove( postId, null );

		Assert.assertNotNull( result );
		Assert.assertTrue( Boolean.valueOf( result.toString() ) );

		System.out.println( result );
	}
}
