package com.codesolid.tests;

import com.codesolid.tutorials.model.entities.*;
import com.codesolid.tutorials.model.dal.Storage;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestStorage {

    @Test
    public void testWrite() {
        // Just a write, verify id set
        Actor user = new Actor();
        Storage<Actor> storage = new Storage<Actor>(user);
        storage.beginTransaction();
        user.setRole("SuperUser");
        assertNull(user.getId());
        storage.insert(user);
        assertNotNull(user.getId());
        storage.commit();
    }

    @Test
    public void testWriteAndRead() {

        // This time write and read back
        String overRideRole = "SuperUser";

        // Write
        Actor actor = new Actor();
        Storage<Actor> storage = new Storage<Actor>(actor);
        storage.beginTransaction();
        actor.setRole(overRideRole);
        assertNull(actor.getId());
        storage.insert(actor);
        assertNotNull(actor.getId());
        Long id = actor.getId();
        storage.commit();

        // Read and verify
        Actor actor2  = new Actor();
        assertEquals(actor2.getRole(), Actor.DEFAULT_ROLE);
        storage = new Storage<Actor>(actor2);
        storage.beginTransaction();
        actor2 = storage.getById(id);
        assertEquals(actor2.getRole(), overRideRole);
        storage.commit();
    }


    @Test
    public void testUpdate() {

        String overRideRole = "SuperUser";
        String newOverrideRole = "GUEST";

        // Insert an actor
        Actor actor = new Actor();
        Storage<Actor> storage = new Storage<Actor>(actor);
        storage.beginTransaction();
        actor.setRole(overRideRole);
        assertNull(actor.getId());
        storage.insert(actor);
        assertNotNull(actor.getId());
        Long id = actor.getId();
        storage.commit();

        // Read it back
        Actor actor2  = new Actor();
        assertEquals(actor2.getRole(), Actor.DEFAULT_ROLE);
        storage.beginTransaction();
        actor2 = storage.getById(id);
        assertEquals(actor2.getRole(), overRideRole);

        // Update it
        actor2.setRole(newOverrideRole);
        storage.update(actor2);
        storage.commit();

        // Read it again and verify update
        storage.beginTransaction();
        Actor actor3  = storage.getById(id);
        assertEquals(actor3.getRole(), newOverrideRole);
        storage.commit();
    }

    @Test
    public void testDelete() {

        Actor actor = new Actor();
        Storage<Actor> storage = new Storage<Actor>(actor);

        // Write
        storage.beginTransaction();
        storage.insert(actor);
        Long id = actor.getId();
        storage.commit();

        // Delete it now
        assert(actor.getId() > 0);
        storage.beginTransaction();
        storage.delete(actor);
        storage.commit();

        // Now we can't read it back, as expected
        storage.beginTransaction();
        Actor actor2 = storage.getById(id);
        assertNull(actor2);
        storage.commit();
    }
}
