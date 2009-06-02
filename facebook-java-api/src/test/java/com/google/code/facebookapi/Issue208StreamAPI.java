package com.google.code.facebookapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.json.JSONObject;
import org.junit.Test;


public class Issue208StreamAPI {
	@Test
	public void testStreamGet() throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		JSONObject result = (JSONObject) client.stream_get( null, null, null, null, null, null, null );

		Assert.assertNotNull( result );
		Assert.assertFalse( StringUtils.isEmpty( result.toString() ) );

		System.out.println( result );
	}

	@Test
	public void testStreamGetLimit() throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		JSONObject result = (JSONObject) client.stream_get( null, null, null, null, 1, null, null );

		Assert.assertNotNull( result );
		Assert.assertFalse( StringUtils.isEmpty( result.toString() ) );

		System.out.println( result );
	}

	@Test
	public void testStreamGetDateRange() throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		Date start = DateUtils.addDays( new Date(), -2 );
		Date end = DateUtils.addDays( new Date(), -1 );

		JSONObject result = (JSONObject) client.stream_get( null, null, start, end, null, null, null );

		Assert.assertNotNull( result );
		Assert.assertFalse( StringUtils.isEmpty( result.toString() ) );

		System.out.println( result );
	}

	@Test
	public void testStreamGetMetadata() throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		List<String> metadata = new ArrayList<String>( 1 );
		metadata.add( "photo_tags" );

		JSONObject result = (JSONObject) client.stream_get( null, null, null, null, null, null, metadata );

		Assert.assertNotNull( result );
		Assert.assertFalse( StringUtils.isEmpty( result.toString() ) );

		System.out.println( result );
	}

	@Test
	public void testStreamGetSourceIds() throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		List<Long> sourceIds = new ArrayList<Long>( 1 );
		sourceIds.add( client.users_getLoggedInUser() );

		Object result = client.stream_get( null, sourceIds, null, null, null, null, null );

		Assert.assertNotNull( result );
		Assert.assertFalse( StringUtils.isEmpty( result.toString() ) );

		System.out.println( result );
	}

	@Test
	public void testStreamPublishAndRemove() throws IOException, FacebookException {
		String postId = streamPublish();
		streamRemove( postId );
	}

	/** Used by various unit tests to create stream item. */
	private String streamPublish() throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		String message = "Facebook stream publish test.";

		Object result = client.stream_publish( message, null, null, null, null );

		Assert.assertNotNull( result );

		System.out.println( result );

		return result.toString();
	}

	/** Used by various unit tests to remove stream item. */
	private void streamRemove( final String postId ) throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		Object result = client.stream_remove( postId, null );

		Assert.assertNotNull( result );
		Assert.assertTrue( Boolean.valueOf( result.toString() ) );

		System.out.println( result );
	}

	@Test
	public void testStreamComments() throws IOException, FacebookException {
		String postId = streamPublish();
		String commentId = streamAddComment( postId );
		streamGetComments( postId );
		streamRemoveComment( commentId );
		streamRemove( postId );
	}

	private String streamAddComment( final String postId ) throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		String comment = "Unit test comment.";
		Object result = client.stream_addComment( postId, comment, null );

		Assert.assertNotNull( result );

		System.out.println( result );

		return result.toString();
	}

	private void streamGetComments( final String postId ) throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		Object result = client.stream_getComments( postId );

		Assert.assertNotNull( result );

		System.out.println( result );
	}

	private void streamRemoveComment( final String postId ) throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		Object result = client.stream_removeComment( postId, null );

		Assert.assertNotNull( result );

		System.out.println( result );
	}

	@Test
	public void testStreamLikes() throws IOException, FacebookException {
		String postId = streamPublish();
		streamAddLike( postId );
		streamRemoveLike( postId );
		streamRemove( postId );
	}

	private String streamAddLike( final String postId ) throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		Object result = client.stream_addLike( postId, null );

		Assert.assertNotNull( result );
		Assert.assertTrue( Boolean.valueOf( result.toString() ) );

		System.out.println( result );

		return result.toString();
	}

	private void streamRemoveLike( final String postId ) throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		Object result = client.stream_removeLike( postId, null );

		Assert.assertNotNull( result );
		Assert.assertTrue( Boolean.valueOf( result.toString() ) );

		System.out.println( result );
	}

	@Test
	public void testStreamGetFilters() throws IOException, FacebookException {
		FacebookJsonRestClient client = FacebookSessionTestUtils.getValidClient( FacebookJsonRestClient.class );

		List<Long> sourceIds = new ArrayList<Long>( 1 );
		sourceIds.add( client.users_getLoggedInUser() );

		Object result = client.stream_getFilters( null );

		Assert.assertNotNull( result );

		System.out.println( result );
	}
}
